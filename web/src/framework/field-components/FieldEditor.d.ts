import React from 'react';
import { SelectProps } from 'antd/es/select';
import {TreeProps} from "antd";

export interface FieldEditProps  {
    value ?: string;
    onChange ?: (value: string) => void;
}

export class FieldEditor extends React.Component<FieldEditProps, any> {}

