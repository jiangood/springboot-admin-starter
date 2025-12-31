import React from "react";
import {Button} from "antd";
import {MessageUtils,} from "../utils";


export class ViewFlowableInstanceProgressButton extends React.Component{
    state = {
        open:false,
    }

    onClick = () => {
        console.log('点击追踪流程')
        MessageUtils.alert(<ViewInstanceProgress value={this.props.value} />,{
            title:'流程审批信息',
            width:800
        })
    };
    render() {
       return <Button onClick={this.onClick} size='small'>追踪流程</Button>
    }


}