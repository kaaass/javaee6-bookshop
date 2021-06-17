package net.kaaass.bookshop.service.metadata;

import java8.util.function.Consumer;
import java8.util.function.Function;
import java8.util.function.Supplier;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import net.kaaass.bookshop.dao.entity.ProductMetadataEntity;
import net.kaaass.bookshop.dao.repository.ProductMetadataRepository;
import net.kaaass.bookshop.util.TimeUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Stateless
public class MetadataManager implements Serializable {

    @Inject
    private ProductMetadataRepository productMetadataRepository;

    /*
     * Product
     */

    public String getForProduct(String productId, String key, String defValue) {
        return productMetadataRepository.findByProductIdAndKey(productId, key)
                .map(new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getValue();
                    }
                })
                .orElse(defValue);
    }

    public String getForProduct(String productId, String key) {
        return getForProduct(productId, key, "");
    }

    public Map<String, String> getAllForProduct(String productId) {
        return StreamSupport.stream(productMetadataRepository.findAllByProductId(productId))
                .collect(Collectors.toMap(new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getKey();
                    }
                }, new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getValue();
                    }
                }));
    }

    public void setForProduct(final String productId, final String key, String value) {
        final ProductMetadataEntity metadata = productMetadataRepository.findByProductIdAndKey(productId, key)
                .orElseGet(new Supplier<ProductMetadataEntity>() {
                    @Override
                    public ProductMetadataEntity get() {
                        final ProductMetadataEntity newEntity = new ProductMetadataEntity();
                        newEntity.setProductId(productId);
                        newEntity.setKey(key);
                        return newEntity;
                    }
                });
        metadata.setValue(value);
        metadata.setLastUpdateTime(TimeUtils.nowTimestamp());
        productMetadataRepository.save(metadata);
    }

    public void deleteForProduct(String productId, String key) {
        productMetadataRepository.findByProductIdAndKey(productId, key)
                .ifPresent(new Consumer<ProductMetadataEntity>() {
                    @Override
                    public void accept(ProductMetadataEntity entity) {
                        productMetadataRepository.delete(entity);
                    }
                });
    }
}
