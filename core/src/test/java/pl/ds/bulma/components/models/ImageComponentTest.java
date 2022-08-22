package pl.ds.bulma.components.models;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContext;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class ImageComponentTest {

    private static final String PATH = "/content/image";
    private final SlingContext context = new SlingContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void init() {
        context.addModelsForClasses(ImageComponent.class);
        context.load().json(requireNonNull(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("image.json")), PATH);
    }

    @Test
    void imageWithFixedType() {
        ImageComponent model = requireNonNull(
                context.resourceResolver().getResource(PATH + "/fixed")).adaptTo(ImageComponent.class);

        assertThat(model).isNotNull();
        assertThat(model.getAssetReference()).isEqualTo("/content/space/assets/imageLg.jpg");
        assertThat(model.getSrc()).isNull();
        assertThat(model.getType()).isEqualTo("fixed");
        assertThat(model.getStyle()).isEqualTo("is-16x16px");
        assertThat(model.getIsRounded()).isEqualTo("true");
        assertThat(model.getAlt()).isEqualTo("Really nice picture");
    }

    @Test
    void imageWithRatioType() {
        ImageComponent model = requireNonNull(
                context.resourceResolver().getResource(PATH + "/ratio")).adaptTo(ImageComponent.class);

        assertThat(model).isNotNull();
        assertThat(model.getAssetReference()).isEqualTo("/content/space/assets/imageLg.jpg");
        assertThat(model.getSrc()).isEqualTo("/content/space/assets/imageSrc.jpg");
        assertThat(model.getType()).isEqualTo("ratio");
        assertThat(model.getStyle()).isEqualTo("is-4by3");
        assertThat(model.getIsRounded()).isNull();
        assertThat(model.getAlt()).isNull();
    }

    @Test
    void overWriteAssetReferenceWhenMissing() {
        ImageComponent model = requireNonNull(
                context.resourceResolver().getResource(PATH + "/srcOverwrite")).adaptTo(ImageComponent.class);

        assertThat(model).isNotNull();
        assertThat(model.getAssetReference()).isEqualTo("/content/space/assets/otherAsset.jpg");
        assertThat(model.getSrc()).isEqualTo("/content/space/assets/otherAsset.jpg");
        assertThat(model.getType()).isEqualTo("fixed");
        assertThat(model.getStyle()).isEqualTo("is-16x16px");
        assertThat(model.getIsRounded()).isNull();
        assertThat(model.getAlt()).isNull();
    }

}