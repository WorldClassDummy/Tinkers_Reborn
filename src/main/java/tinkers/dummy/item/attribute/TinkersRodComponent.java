package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersRodComponent(ToolPart part, PartMaterial material) {
    public static final Codec<TinkersRodComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ToolPart.CODEC.fieldOf("part").forGetter(TinkersRodComponent::part),
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersRodComponent::material)
            ).apply(instance, TinkersRodComponent::new));

    public static final StreamCodec<ByteBuf, TinkersRodComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(ToolPart.CODEC), TinkersRodComponent::part,
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersRodComponent::material,
            TinkersRodComponent::new
    );
}
