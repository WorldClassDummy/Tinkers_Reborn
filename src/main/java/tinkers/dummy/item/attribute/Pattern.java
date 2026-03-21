package tinkers.dummy.item.attribute;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.resources.ResourceLocation;
import tinkers.dummy.TinkersReborn;

import java.util.HashMap;
import java.util.Map;

public class Pattern {
    public static final Map<ResourceLocation, Pattern> ID = new HashMap<>();

    public static Pattern register(String name, int materialAmount) {
        ResourceLocation id = TinkersReborn.id(name);
        Pattern pattern = new Pattern(id, materialAmount);
        ID.put(id, pattern);
        return pattern;
    }

    private final ResourceLocation id;
    private final int materialAmount;

    public Pattern(ResourceLocation id, int materialAmount) {
        this.id = id;
        this.materialAmount = materialAmount;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public int getMaterialRequirement() {
        return this.materialAmount;
    }

    public Pattern getMaterial(ResourceLocation id) {
        return ID.get(id);
    }

    public static PrimitiveCodec<Pattern> CODEC = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<Pattern> read(final DynamicOps<T> ops, final T input) {
            return ResourceLocation.CODEC.parse(ops, input).flatMap(id -> {
                Pattern material = ID.get(id);
                return material != null
                        ? DataResult.success(material)
                        : DataResult.error(() -> "Unknown Material: " + id);
            });
        }

        @Override
        public <T> T write(final DynamicOps<T> ops, final Pattern value) {
            ResourceLocation id = value.getId();
            return ResourceLocation.CODEC.encodeStart(ops, id).getOrThrow();
        }

        @Override
        public String toString() {
            return "Pattern";
        }
    };
}
