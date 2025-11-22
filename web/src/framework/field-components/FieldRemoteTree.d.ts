import React from 'react';
import { SelectProps } from 'antd/es/select';
import {TreeProps} from "antd";

export interface FieldRemoteTreeProps  {

    /**
     * 请求地址
     */
    url: string ;
}

export class FieldRemoteTree extends React.Component<FieldRemoteTreeProps, any> {}

