import {TextFieldEntry, isTextFieldEntryEdited, SelectEntry} from '@bpmn-io/properties-panel';
import { useService } from 'bpmn-js-properties-panel';
import { useEffect, useState } from '@bpmn-io/properties-panel/preact/hooks';
import {HttpUtils} from "../../../../../../framework";
export  function ServiceTaskProps () {

  return [
    {
      id: 'delegateExpression',
      component: ServiceTaskComponent,
      isEdited: isTextFieldEntryEdited,
    }

  ];
}

function ServiceTaskComponent(props) {
  const { element, id } = props;

  const modeling = useService('modeling');
  const debounce = useService('debounceInput');

  const getValue = (element) => {
    return element.businessObject.delegateExpression || '';
  };

  const setValue = value => {
    return modeling.updateProperties(element, {
      delegateExpression: value
    });
  };

  const [ options, setOptions ] = useState([]);

  useEffect(async () => {
    const rs = await HttpUtils.get('admin/flowable/model/javaDelegateOptions')
    setOptions(rs)
  }, [ setOptions ]);

  return SelectEntry({
    element,
    id: id,
    label: 'Java服务类表达式',
    description: '实现了JavaDelegate的Bean名称表达式， 如 ${demoDelegate}',
    getValue,
    setValue,
    debounce,

    getOptions: () => {
      return options
    }
  })

}
