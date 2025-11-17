import {Form, Input} from "antd";

export default function (){

    return <div>
        demo表单示例
        <Form>
            <Form.Item label="用户名">
                <Input/>
            </Form.Item>
            <Form.Item label="密码">
                <Input/>
            </Form.Item>
        </Form>
    </div>
}