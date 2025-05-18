package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.dtos.products.productImage.RequestProductImageDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.interfaces.IStorageServiceAdapter;
import com.davi.restaurant_burguer.mappers.ProductMapper;
import com.davi.restaurant_burguer.models.Product;
import com.davi.restaurant_burguer.models.ProductImage;
import com.davi.restaurant_burguer.repositories.ProductImageRepository;
import com.davi.restaurant_burguer.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;
    private final IStorageServiceAdapter storageServiceAdapter;

    public ProductService(ProductRepository productRepository,ProductImageRepository productImageRepository, ProductMapper productMapper, IStorageServiceAdapter storageServiceAdapter) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productMapper = productMapper;
        this.storageServiceAdapter = storageServiceAdapter;
    }
    public ResponseProductDTO getProductByUuid(String uuid){
        Product product = this.productRepository.getByUuid(uuid);
        if(product == null){
            throw new NotfoundException("produto não encontrado");
        }
        return this.productMapper.mapToResponseProductDTO(product);
    }

    public List<ResponseProductDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.mapToResponseProductDTOList(products);
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
        int orders = allImages.size();

        String fileName = product.getName().replaceAll(" ","_") + "_" + orders + "_" + requestProductImageDTO.uuid() + "_" + OffsetDateTime.now().toEpochSecond();
        String url = this.storageServiceAdapter
                .uploadFile(requestProductImageDTO.file().getBytes(),fileName,requestProductImageDTO.file().getContentType());
        ProductImage image = new ProductImage(product,url, requestProductImageDTO.isThumbnail(), orders);
        this.productImageRepository.save(image);
    }

    private static Product editProduct(Product product, RequestProductDTO requestProductDTO){
        product.setName(requestProductDTO.name());
        product.setCategory(requestProductDTO.category());
        product.setDescription(requestProductDTO.description());
        product.setPrice(requestProductDTO.price());
        product.setAvailable(requestProductDTO.isAvailable());
        return product;
    }
}
