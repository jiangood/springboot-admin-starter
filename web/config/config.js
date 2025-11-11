import {defineConfig} from 'umi';
import {defaultConfig} from "./defaultConfig";

defaultConfig.alias= {
    '@jian41/admin-framework': join(__dirname, 'src'),
}

export default defineConfig(defaultConfig);
