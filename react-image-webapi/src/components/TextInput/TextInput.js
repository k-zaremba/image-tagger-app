import React, { useState, useEffect } from "react";
import './TextInput.css'
import { Input } from 'antd';
import { LinkOutlined } from '@ant-design/icons';

const TextInput = (props) => {
    const [appState, setAppState] = useState(props.appState);
    const [url, setUrl] = useState('');

    const handleInputChange = (event) => {
        setUrl(event.target.value);
    }

    useEffect(() => {
        setAppState((state) => {
            state.url = url;
            return state
        });
    }, [url])

    return (
        <div>
            <Input onChange={handleInputChange} allowClear="true" prefix={<LinkOutlined />} className='urlText' placeholder={"enter image URL here..."} type='text'></Input>
        </div>
    )
}

export default TextInput