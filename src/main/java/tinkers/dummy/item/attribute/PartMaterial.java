package tinkers.dummy.item.attribute;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.resources.ResourceLocation;
import tinkers.dummy.TinkersReborn;

import java.util.HashMap;
import java.util.Map;

public class PartMaterial {
    public static final Map<ResourceLocation, PartMaterial> ID = new HashMap<>();

    public static PartMaterial register(String name) {
        ResourceLocation id = TinkersReborn.id(name);
        PartMaterial material = new PartMaterial(id);
        ID.put(id, material);
        return material;
    }

    private final ResourceLocation id;

    public PartMaterial(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public PartMaterial getMaterial(ResourceLocation id) {
        return ID.get(id);
    }

    public static PrimitiveCodec<PartMaterial> CODEC = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<PartMaterial> read(final DynamicOps<T> ops, final T input) {
            return ResourceLocation.CODEC.parse(ops, input).flatMap(id -> {
                PartMaterial material = ID.get(id);
                return material != null
                        ? DataResult.success(material)
                        : DataResult.error(() -> "Unknown Material: " + id);
            });
        }

        @Override
        public <T> T write(final DynamicOps<T> ops, final PartMaterial value) {
            ResourceLocation id = value.getId();
            return ResourceLocation.CODEC.encodeStart(ops, id).getOrThrow();
        }

        @Override
        public String toString() {
            return "Material";
        }
    };
}
