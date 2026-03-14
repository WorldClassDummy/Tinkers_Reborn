package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersMaterialComponent(PartMaterial material) {
    public static final Codec<TinkersMaterialComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersMaterialComponent::material)
            ).apply(instance, TinkersMaterialComponent::new));

    public static final StreamCodec<ByteBuf, TinkersMaterialComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersMaterialComponent::material,
            TinkersMaterialComponent::new
    );
}
