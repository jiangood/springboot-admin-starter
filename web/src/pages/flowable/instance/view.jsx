import React from "react";
import InstanceStatusInfo from "../../../components/InstanceStatusInfo";
import {PageUtils} from "../../../framework";

export default class extends React.Component {


    render() {
        const {businessKey, id} = PageUtils.currentParams()
        return <InstanceStatusInfo businessKey={businessKey} id={id}/>
    }

}
