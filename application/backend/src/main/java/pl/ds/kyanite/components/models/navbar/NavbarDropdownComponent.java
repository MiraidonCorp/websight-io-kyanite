/*
 * Copyright (C) 2023 Dynamic Solutions
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

package pl.ds.kyanite.components.models.navbar;

import java.util.List;
import javax.inject.Inject;
import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavbarDropdownComponent {

  @Inject
  @Getter
  @Default(values = "Label")
  private String label;

  @Inject
  @Getter
  @Default(booleanValues = true)
  private boolean hasArrow;

  @Inject
  @Getter
  private boolean isBoxed;

  @Inject
  @Getter
  private boolean isDropup;

  @Inject
  @Getter
  private boolean addIcon;

  @Inject
  @Getter
  private String icon;

  @Inject
  @Getter
  private List<NavbarItemComponent> items;
}