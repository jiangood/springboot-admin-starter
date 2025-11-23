import {
    FieldDateTimePickerString,
    FieldImageBase64,
    FieldRadioBoolean,
    FieldUploadImage
} from "../field";
import {ViewImage,  ViewText} from "../view";
import {ViewPassword,ViewBoolean} from "../../view-components";
import {FieldDictSelect, FieldRemoteSelect} from "../../field-components";
import {Input} from "antd";


export const fieldRegistry = {
    'text':Input,

    'dict':FieldDictSelect,
    'password':Input.Password,
    'datetime':FieldDateTimePickerString,
    'boolean':FieldRadioBoolean,
    imageBase64:FieldImageBase64,
    image:FieldUploadImage,

    select: FieldRemoteSelect
}

export const viewRegistry = {
    text: ViewText,
    password: ViewPassword,
    boolean: ViewBoolean,
    imageBase64: ViewImage,
    image:ViewImage
}




