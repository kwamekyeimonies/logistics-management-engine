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
import java.util.Optional;
import java.util.UUID;

import static logistics_management_engine.common.Messages.PRODUCT_SUPPLIER_CREATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class ProductSupplierService implements IProductSupplierService{
    private final ProductSupplierRepository productSupplierRepository;
    @Override
    public List<ProductSupplier> getAllProductSuppliersCreateByEmployee(Employee employee) {
        return productSupplierRepository.findSuppliersByEmployee(employee.getId());
    }

    @Override
    public List<ProductSupplier> getAllProductSuppliers() {
        return productSupplierRepository.findAllActiveSuppliers();
    }

    @Override
    public ProductSupplier getProductSupplier(Employee employee, UUID productSupplierId) {
        return productSupplierRepository.findSuppliersByEmployee(employee.getId())
                .stream()
                .filter(supplier -> supplier.getId().equals(productSupplierId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product Supplier not found with ID: " + productSupplierId));
    }


    @Override
    public CreateProductSupplierResponse addProductSupplier(CreateProductSupplierRequest productSupplierRequest, Employee employee) {
       try
       {
           ProductSupplier productSupplier;
           productSupplier = ProductSupplier.builder()
                   .Id(UUID.randomUUID())
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
                   .supplierId(productSupplier.getId())
                   .supplierName(productSupplier.getSupplierName())
                   .build();

           return createProductSupplierResponse;
       }catch (Exception exception){
           throw new RuntimeException(exception.getMessage());
       }
    }

    @Override
    public UpdateProductSupplierResponse updateProductSupplier(
            UUID productSupplierId,
            CreateProductSupplierRequest productSupplierRequest,
            Employee employee) {
        try {
            ProductSupplier productSupplier = productSupplierRepository.findById(productSupplierId)
                    .orElseThrow(() -> new RuntimeException("Product Supplier not found"));

            productSupplier.setSupplierName(productSupplierRequest.getSupplierName());
            productSupplier.setContactName(productSupplierRequest.getContactName());
            productSupplier.setContactEmail(productSupplierRequest.getContactEmail());
            productSupplier.setContactPhone(productSupplierRequest.getContactPhone());
            productSupplier.setAddress(productSupplierRequest.getAddress());
            productSupplier.setCity(productSupplierRequest.getCity());
            productSupplier.setState(productSupplierRequest.getState());
            productSupplier.setCountry(productSupplierRequest.getCountry());
            productSupplier.setPostalCode(productSupplierRequest.getPostalCode());
            productSupplier.setStatus(productSupplierRequest.getState());
            productSupplier.setUpdatedDate(LocalDateTime.now());
            productSupplier.setUpdatedBy(employee.getId());

            productSupplierRepository.save(productSupplier);

            return UpdateProductSupplierResponse.builder()
                    .supplierName(productSupplier.getSupplierName())
                    .supplierId(productSupplier.getId())
                    .message("Product Supplier Updated")
                    .build();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update product supplier: " + exception.getMessage());
        }
    }

    @Override
    public String deleteProductSupplier(UUID productSupplierId, Employee employee) {
        try {
            ProductSupplier productSupplier = productSupplierRepository.findById( productSupplierId)
                    .orElseThrow(() -> new RuntimeException("Product Supplier not found with ID: " + productSupplierId));

            if (productSupplier.isDeleted()) {
                return "Product Supplier is already deleted";
            }

            productSupplier.setDeleted(true);
            productSupplier.setDeletedAt(LocalDateTime.now());
            productSupplierRepository.save(productSupplier);

            return "Product Supplier marked as deleted successfully";
        } catch (Exception exception) {
            throw new RuntimeException(String.format("Error deleting product supplier: %s", exception.getMessage()));
        }
    }


}
