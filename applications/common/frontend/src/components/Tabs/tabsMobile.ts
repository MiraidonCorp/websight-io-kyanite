/*
 * Copyright (C) 2024 Dynamic Solutions
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

const accordionItemClass = 'tab-mobile';
const accordionButtonClass = 'tab-mobile-title';
const openedClass= 'opened';

const handleAccordionOpening = (accordionItem) => {
  const accordionButton = accordionItem.querySelector(`.${accordionButtonClass}`);
  console.log(accordionButton);

  accordionButton.addEventListener('click', () => {
    console.log(accordionItem);
    console.log(accordionItem.classList);

    accordionItem.classList.toggle(openedClass);
    accordionButton.ariaExpanded = accordionItem.classList.contains(openedClass)
        ? 'true'
        : 'false';
  });
};

const initAccordion = () => {
  document.addEventListener(
      'DOMContentLoaded',
      () => {
        const elements = document.querySelectorAll(`.${accordionItemClass}`);

        if (!elements?.length) {
          return;
        }

        elements.forEach((accordionItem) => handleAccordionOpening(accordionItem));
      },
      { once: true }
  );
};

initAccordion();
