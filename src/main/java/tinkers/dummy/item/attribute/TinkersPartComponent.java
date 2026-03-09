package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersPartComponent(PartMaterial material) {
    public static final Codec<TinkersPartComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersPartComponent::material)
            ).apply(instance, TinkersPartComponent::new));

    public static final StreamCodec<ByteBuf, TinkersPartComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersPartComponent::material,
            TinkersPartComponent::new
    );
}
