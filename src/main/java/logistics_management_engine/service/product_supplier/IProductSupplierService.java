package logistics_management_engine.service.product_supplier;

import logistics_management_engine.dto.CreateProductSupplierRequest;
import logistics_management_engine.dto.CreateProductSupplierResponse;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductSupplier;

import java.util.List;
import java.util.UUID;

public interface IProductSupplierService {
    List<ProductSupplier> getAllProductSuppliers(Employee employee);
    ProductSupplier getProductSupplier(Employee employee, UUID productSupplierId);
    CreateProductSupplierResponse addProductSupplier(CreateProductSupplierRequest productSupplierRequest, Employee employee);
    ProductSupplier updateProductSupplier(UUID productSupplierId, CreateProductSupplierRequest productSupplierRequest);
}
