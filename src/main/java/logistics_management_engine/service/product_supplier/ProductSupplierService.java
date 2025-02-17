package logistics_management_engine.service.product_supplier;

import logistics_management_engine.dto.CreateProductSupplierRequest;
import logistics_management_engine.dto.CreateProductSupplierResponse;
import logistics_management_engine.dto.UpdateProductSupplierResponse;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductSupplier;

import logistics_management_engine.repository.ProductSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static logistics_management_engine.common.Messages.PRODUCT_SUPPLIER_CREATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class ProductSupplierService implements IProductSupplierService{
    private final ProductSupplierRepository productSupplierRepository;
    @Override
    public List<ProductSupplier> getAllProductSuppliersCreateByEmployee(Employee employee) {
        return productSupplierRepository.findByCreatedByEmployee(employee);
    }

    @Override
    public List<ProductSupplier> getAllProductSuppliers() {
        return productSupplierRepository.findAll();
    }

    @Override
    public ProductSupplier getProductSupplier(Employee employee, UUID productSupplierId) {
        return productSupplierRepository.findBySupplierIdAndCreatedByEmployee(productSupplierId, employee)
                .orElseThrow(() -> new RuntimeException("Product supplier not found with ID: " + productSupplierId));
    }

    @Override
    public CreateProductSupplierResponse addProductSupplier(CreateProductSupplierRequest productSupplierRequest, Employee employee) {
       try
       {
           ProductSupplier productSupplier;
           productSupplier = ProductSupplier.builder()
                   .supplierId(UUID.randomUUID())
                   .supplierName(productSupplierRequest.getSupplierName())
                   .contactName(productSupplierRequest.getContactName())
                   .contactEmail(productSupplierRequest.getContactEmail())
                   .contactPhone(productSupplierRequest.getContactPhone())
                   .address(productSupplierRequest.getAddress())
                   .city(productSupplierRequest.getCity())
                   .state(productSupplierRequest.getState())
                   .country(productSupplierRequest.getCountry())
                   .postalCode(productSupplierRequest.getPostalCode())
                   .status("ACTIVE")
                   .createdDate(LocalDateTime.now())
                   .updatedDate(null)
                   .createdByEmployee(employee)
                   .updatedBy(null)
                   .build();

           productSupplierRepository.save(productSupplier);

           CreateProductSupplierResponse createProductSupplierResponse = CreateProductSupplierResponse
                   .builder()
                   .message(PRODUCT_SUPPLIER_CREATED_SUCCESSFULLY)
                   .supplierId(productSupplier.getSupplierId())
                   .supplierName(productSupplier.getSupplierName())
                   .build();

           return createProductSupplierResponse;
       }catch (Exception exception){
           throw new RuntimeException(exception.getMessage());
       }
    }

    @Override
    public UpdateProductSupplierResponse updateProductSupplier(UUID productSupplierId, CreateProductSupplierRequest productSupplierRequest, Employee employee) {
        try {
            ProductSupplier productSupplier = productSupplierRepository.findBySupplierId(productSupplierId);
            if (productSupplier == null) {
                throw new RuntimeException("Product Supplier not found");
            }

            productSupplier.setSupplierName(productSupplier.getSupplierName());
            productSupplier.setContactName(productSupplier.getContactName());
            productSupplier.setContactEmail(productSupplier.getContactEmail());
            productSupplier.setContactPhone(productSupplier.getContactPhone());
            productSupplier.setAddress(productSupplier.getAddress());
            productSupplier.setCity(productSupplier.getCity());
            productSupplier.setState(productSupplier.getState());
            productSupplier.setCountry(productSupplier.getCountry());
            productSupplier.setPostalCode(productSupplier.getPostalCode());
            productSupplier.setStatus(productSupplier.getStatus());
            productSupplier.setUpdatedDate(LocalDateTime.now());
            productSupplier.setUpdatedBy(employee.getId());

            productSupplierRepository.save(productSupplier);

            UpdateProductSupplierResponse updateProductSupplierResponse = UpdateProductSupplierResponse
                    .builder()
                    .supplierName(productSupplier.getSupplierName())
                    .supplierId(productSupplier.getSupplierId())
                    .message("Product Supplier Updated")
                    .build();

            return updateProductSupplierResponse;

        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }

    }


}
