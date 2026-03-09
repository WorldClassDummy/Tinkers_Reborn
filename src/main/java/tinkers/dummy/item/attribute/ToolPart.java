package tinkers.dummy.item.attribute;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.resources.ResourceLocation;
import tinkers.dummy.TinkersReborn;

import java.util.HashMap;
import java.util.Map;

public class ToolPart {
    public static final Map<ResourceLocation, ToolPart> ID = new HashMap<>();

    public static ToolPart register(String name) {
        ResourceLocation id = TinkersReborn.id(name);
        ToolPart part = new ToolPart(id);
        ID.put(id, part);
        return part;
    }

    private final ResourceLocation id;

    public ToolPart(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public ToolPart getPart(ResourceLocation id) {
        return ID.get(id);
    }

    public static PrimitiveCodec<ToolPart> CODEC = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<ToolPart> read(final DynamicOps<T> ops, final T input) {
            return ResourceLocation.CODEC.parse(ops, input).flatMap(id -> {
                ToolPart part = ID.get(id);
                return part != null
                        ? DataResult.success(part)
                        : DataResult.error(() -> "Unknown Part: " + id);
            });
        }

        @Override
        public <T> T write(final DynamicOps<T> ops, final ToolPart value) {
            ResourceLocation id = value.getId();
            return ResourceLocation.CODEC.encodeStart(ops, id).getOrThrow();
        }

        @Override
        public String toString() {
            return "Part";
        }
    };
}
