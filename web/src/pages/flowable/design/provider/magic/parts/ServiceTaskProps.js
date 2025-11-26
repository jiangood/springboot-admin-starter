import { html } from 'htm/preact';

import { TextFieldEntry, isTextFieldEntryEdited } from '@bpmn-io/properties-panel';
import { useService } from 'bpmn-js-properties-panel';

export default function(element) {

  return [
    {
      id: 'spell',
      element,
      component: ServiceTaskComponent,
      isEdited: isTextFieldEntryEdited,
    }

  ];
}

function ServiceTaskComponent(props) {
  const { element, id } = props;

  const modeling = useService('modeling');
  const debounce = useService('debounceInput');

  const getValue = () => {
    return element.businessObject.spell || '';
  };

  const setValue = value => {
    return modeling.updateProperties(element, {
      spell: value
    });
  };

  return html`<${TextFieldEntry}
    id=${ id }
    element=${ element }
    description='支持表达式'
    label='监听类'
    getValue=${ getValue }
    setValue=${ setValue }
    debounce=${ debounce }
  />`;
}
