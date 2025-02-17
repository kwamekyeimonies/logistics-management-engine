package logistics_management_engine.service.product_supplier;

import logistics_management_engine.dto.CreateProductSupplierRequest;
import logistics_management_engine.dto.CreateProductSupplierResponse;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductSupplier;
import logistics_management_engine.repository.EmployeeRepository;
import logistics_management_engine.service.auth.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static logistics_management_engine.common.Messages.PRODUCT_SUPPLIER_CREATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class ProductSupplierService implements IProductSupplierService{
    @Override
    public List<ProductSupplier> getAllProductSuppliers(Employee employee) {
        return List.of();
    }

    @Override
    public ProductSupplier getProductSupplier(Employee employee, UUID productSupplierId) {
        return null;
    }

    @Override
    public CreateProductSupplierResponse addProductSupplier(CreateProductSupplierRequest productSupplierRequest, Employee employee) {
       try
       {
           ProductSupplier productSupplier;
           productSupplier = ProductSupplier.builder()
                   .supplierId(UUID.randomUUID())
                   .city(productSupplierRequest.getCity())
                   .address(productSupplierRequest.getAddress())
                   .supplierName(productSupplierRequest.getSupplierName())
                   .contactEmail(productSupplierRequest.getContactEmail())
                   .contactName(productSupplierRequest.getContactName())
                   .contactPhone(productSupplierRequest.getContactPhone())
                   .createdByEmployee(employee)
                   .build();


           CreateProductSupplierResponse createProductSupplierResponse = CreateProductSupplierResponse
                   .builder()
                   .message(PRODUCT_SUPPLIER_CREATED_SUCCESSFULLY)
                   .supplierName(productSupplier.getSupplierName())
                   .build();

           return createProductSupplierResponse;
       }catch (Exception exception){
           throw new RuntimeException(exception.getMessage());
       }
    }

    @Override
    public ProductSupplier updateProductSupplier(UUID productSupplierId, CreateProductSupplierRequest productSupplierRequest) {
        return null;
    }
}
