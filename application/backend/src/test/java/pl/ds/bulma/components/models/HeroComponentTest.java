/*
 * Copyright (C) 2022 Dynamic Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
class HeroComponentTest {

  private static final String PATH = "/content/hero";
  private final SlingContext context = new SlingContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

  @BeforeEach
  public void init() {
    context.addModelsForClasses(HeroComponent.class);
    context.load().json(requireNonNull(
        Thread.currentThread().getContextClassLoader().getResourceAsStream("hero.json")), PATH);
  }

  @Test
  void defaultHero() {
    HeroComponent model = requireNonNull(
        context.resourceResolver().getResource(PATH + "/default")).adaptTo(HeroComponent.class);

    assertThat(model).isNotNull();
    assertThat(model.getSize()).isNull();
    assertThat(model.getVariant()).isNull();
    assertThat(model.getHeroClasses()).isEmpty();
  }

  @Test
  void onlySizeClass() {
    HeroComponent model = requireNonNull(
        context.resourceResolver().getResource(PATH + "/onlySize")).adaptTo(HeroComponent.class);

    assertThat(model).isNotNull();
    assertThat(model.getSize()).isEqualTo("is-medium");
    assertThat(model.getVariant()).isNull();
    assertThat(model.getHeroClasses()).containsExactlyInAnyOrder("is-medium");
  }

  @Test
  void bothStyles() {
    HeroComponent model = requireNonNull(
        context.resourceResolver().getResource(PATH + "/bothStyles")).adaptTo(HeroComponent.class);

    assertThat(model).isNotNull();
    assertThat(model.getSize()).isEqualTo("is-medium");
    assertThat(model.getVariant()).isEqualTo("is-warning");
    assertThat(model.getHeroClasses()).containsExactlyInAnyOrder("is-medium", "is-warning");
  }

  @Test
  void onlyVariantClass() {
    HeroComponent model = requireNonNull(
        context.resourceResolver().getResource(PATH + "/onlyVariant")).adaptTo(HeroComponent.class);

    assertThat(model).isNotNull();
    assertThat(model.getSize()).isNull();
    assertThat(model.getVariant()).isEqualTo("is-warning");
    assertThat(model.getHeroClasses()).containsExactlyInAnyOrder("is-warning");
  }

}