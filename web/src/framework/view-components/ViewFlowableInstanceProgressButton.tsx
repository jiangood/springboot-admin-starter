import React from "react";
import {Button} from "antd";
import {MessageUtils,} from "../utils";
import {ViewStringProps} from "./ViewProps";
import {ViewFlowableInstanceProgress} from "./ViewFlowableInstanceProgress";


export class ViewFlowableInstanceProgressButton extends React.Component<ViewStringProps,any>{
    state = {
        open:false,
    }

    onClick = () => {
        console.log('点击追踪流程')
        let content = <ViewFlowableInstanceProgress value={this.props.value} />;
        MessageUtils.alert(content,{
            title:'流程审批信息',
            width:800
        })
    };
    render() {
       return <Button onClick={this.onClick} size='small'>追踪流程</Button>
    }


}