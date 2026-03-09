package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersToolComponent(ToolPart part, PartMaterial material) {
    public static final Codec<TinkersToolComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ToolPart.CODEC.fieldOf("part").forGetter(TinkersToolComponent::part),
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersToolComponent::material)
            ).apply(instance, TinkersToolComponent::new));

    public static final StreamCodec<ByteBuf, TinkersToolComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(ToolPart.CODEC), TinkersToolComponent::part,
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersToolComponent::material,
            TinkersToolComponent::new
    );
}
