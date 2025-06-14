package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.dtos.products.additional.RequestProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.additional.ResponseProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.productImage.RequestProductImageDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.interfaces.IStorageServiceAdapter;
import com.davi.restaurant_burguer.mappers.ProductMapper;
import com.davi.restaurant_burguer.models.Product;
import com.davi.restaurant_burguer.models.ProductImage;
import com.davi.restaurant_burguer.repositories.ProductImageRepository;
import com.davi.restaurant_burguer.repositories.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;
    private final IStorageServiceAdapter storageServiceAdapter;
    private static final String KEY_PRODUCT_IMAGE = "products-image/";
    private final AdditionalService additionalService;

    public ProductService(ProductRepository productRepository,ProductImageRepository productImageRepository, ProductMapper productMapper, IStorageServiceAdapter storageServiceAdapter, AdditionalService additionalService) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productMapper = productMapper;
        this.storageServiceAdapter = storageServiceAdapter;
        this.additionalService = additionalService;
    }
    public ResponseProductDTO getProductByUuid(String uuid){
        Product product = this.productRepository.getByUuid(uuid);
        if(product == null){
            throw new NotfoundException("produto não encontrado");
        }
        return this.productMapper.mapToResponseProductDTO(product);
    }

    @Cacheable("products")
    public List<ResponseProductDTO> getAllProductsCacheable() {
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.mapToResponseProductDTOList(products);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> getByIdIn(List<Long> ids) {
        return this.productRepository.findByIdIn(ids).orElseThrow(() -> new NotfoundException("produto não encontrado"));
    }

    public ResponseProductDTO saveProduct(RequestProductDTO requestProductDTO){
        Product product = this.productMapper.mapToProduct(requestProductDTO);
        this.productRepository.save(product);
        return this.productMapper.mapToResponseProductDTO(product);
    }

    public ResponseProductDTO updateProduct(RequestProductDTO requestProductDTO, String uuid){
        Product product = this.productRepository.getByUuid(uuid);
        if(product == null){
            throw new NotfoundException("produto não encontrado");
        }
        Product updatedProduct = editProduct(product, requestProductDTO);
        this.productRepository.save(updatedProduct);
        return this.productMapper.mapToResponseProductDTO(updatedProduct);
    }

    public void deleteProduct(String uuid){
        Product product = this.productRepository.getByUuid(uuid);
        if(product == null){
            throw new NotfoundException("produto não encontrado");
        }
        product.setDeletedAt(OffsetDateTime.now());
        this.productRepository.save(product);
    }

    public void uploadProductImage(RequestProductImageDTO requestProductImageDTO) throws IOException {
        Product product = this.productRepository.getByUuid(requestProductImageDTO.uuid());
        if(product == null){
            throw new NotfoundException("produto não encontrado");
        }
        List<ProductImage> allImages = this.productImageRepository.findAllByProductId(product.getId());
        this.updateListWithoutThumbnail(allImages);
        int orders = allImages.size();

        String fileName = product.getName().replaceAll(" ","_") + "_" + orders + "_" + requestProductImageDTO.uuid() + "_" + OffsetDateTime.now().toEpochSecond();
        String url = this.storageServiceAdapter
                .uploadFile(requestProductImageDTO.file().getBytes(),KEY_PRODUCT_IMAGE+fileName,requestProductImageDTO.file().getContentType());
        ProductImage image = new ProductImage(product,url, requestProductImageDTO.isThumbnail(), orders);
        this.productImageRepository.save(image);
    }

    public void saveAdditional(String productUuid, RequestProductAdditionalDTO productAdditionalDTO) {
        Product product = this.productRepository.getByUuid(productUuid);
        if(product == null) {
            throw new NotfoundException("produto não encontrado");
        }
        this.additionalService.saveProductAdditional(product, productAdditionalDTO);
    }

    public ResponseProductAdditionalDTO findOneProductAdditional(Long productAdditional) {
        return this.additionalService.findOne(productAdditional);
    }

    public List<ResponseProductAdditionalDTO> findAllProductAdditional(String productUuid) {
        Product product = this.productRepository.getByUuid(productUuid);
        return this.additionalService.findAllByProductId(product.getId());
    }

    public void deleteProductAdditional(Long productAdditional) {
        this.additionalService.delete(productAdditional);
    }

    public ResponseProductAdditionalDTO updateProductAdditional(Long productAdditionalId,RequestProductAdditionalDTO requestProductAdditionalDTO) throws IllegalArgumentException{
        return this.additionalService.updateProductAdditional(productAdditionalId, requestProductAdditionalDTO);
    }

    private void updateListWithoutThumbnail(List<ProductImage> allImages) {
        for(ProductImage img : allImages) {
            if (img.isThumbnail()) {
                img.setThumbnail(false);
            }
        }
        this.productImageRepository.saveAll(allImages);
    }

    private static Product editProduct(Product product, RequestProductDTO requestProductDTO){
        product.setName(requestProductDTO.name());
        product.setCategory(requestProductDTO.category());
        product.setDescription(requestProductDTO.description());
        product.setPrice(new BigDecimal(requestProductDTO.price()));
        product.setAvailable(requestProductDTO.isAvailable());
        return product;
    }
}
