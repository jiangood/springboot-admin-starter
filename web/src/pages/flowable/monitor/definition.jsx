import React from "react";
import {HttpUtils, ProTable} from "../../../framework";

export default class  extends React.Component {

    columns = [
            {
                title: 'ID',
                dataIndex: 'id',
                key: 'id',
            },
            {
                title: '分类',
                dataIndex: 'category',
                key: 'category',
            },
            {
                title: '名称',
                dataIndex: 'name',
                key: 'name',
            },
            {
                title: '键',
                dataIndex: 'key',
                key: 'key',
            },
            {
                title: '描述',
                dataIndex: 'description',
                key: 'description',
            },
            {
                title: '版本',
                dataIndex: 'version',
                key: 'version',
            },
            {
                title: '资源名称',
                dataIndex: 'resourceName',
                key: 'resourceName',
            },
            {
                title: '部署ID',
                dataIndex: 'deploymentId',
                key: 'deploymentId',
            },
            {
                title: '图表资源名称',
                dataIndex: 'diagramResourceName',
                key: 'diagramResourceName',
            },
            {
                title: '是否有开始表单键',
                dataIndex: 'hasStartFormKey',
                key: 'hasStartFormKey',
                render: (value) => value ? '是' : '否',
            },
            {
                title: '是否有图形符号',
                dataIndex: 'hasGraphicalNotation',
                key: 'hasGraphicalNotation',
                render: (value) => value ? '是' : '否',
            },
            {
                title: '是否挂起',
                dataIndex: 'suspended',
                key: 'suspended',
                render: (value) => value ? '是' : '否',
            },
            {
                title: '租户ID',
                dataIndex: 'tenantId',
                key: 'tenantId',
            },
            {
                title: '派生自',
                dataIndex: 'derivedFrom',
                key: 'derivedFrom',
            },
            {
                title: '根派生来源',
                dataIndex: 'derivedFromRoot',
                key: 'derivedFromRoot',
            },
            {
                title: '派生版本',
                dataIndex: 'derivedVersion',
                key: 'derivedVersion',
            },


    ]

    render() {
        return <ProTable
            search={false}
            columns={this.columns}
            request={(params) => HttpUtils.get('admin/flowable/monitor/definitionPage', params)}
        >

        </ProTable>
    }
}
