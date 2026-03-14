package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersHeadComponent(ToolPart part, PartMaterial material) {
    public static final Codec<TinkersHeadComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ToolPart.CODEC.fieldOf("part").forGetter(TinkersHeadComponent::part),
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersHeadComponent::material)
            ).apply(instance, TinkersHeadComponent::new));

    public static final StreamCodec<ByteBuf, TinkersHeadComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(ToolPart.CODEC), TinkersHeadComponent::part,
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersHeadComponent::material,
            TinkersHeadComponent::new
    );
}
