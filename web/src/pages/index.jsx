import React from "react";
import {Card} from "antd";
import {MessageUtils} from "../framework";


export default class extends React.Component {

    state = {}

    componentDidMount() {
        MessageUtils.alert('1')
    }

    render() {

        return <Card>
            欢迎使用本系统




        </Card>
    }

}
