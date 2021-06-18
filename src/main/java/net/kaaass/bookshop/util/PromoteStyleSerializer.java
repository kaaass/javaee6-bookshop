package net.kaaass.bookshop.util;

import net.kaaass.bookshop.dto.PromoteStyle;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * 打折样式序列化
 *
 * @author kaaass
 */
public class PromoteStyleSerializer extends JsonSerializer<PromoteStyle> {
    @Override
    public void serialize(PromoteStyle promoteStyle, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(promoteStyle.getStyle());
    }
}
