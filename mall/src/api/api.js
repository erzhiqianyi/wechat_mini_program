import {
    wxRequest
} from '@/utils/wxRequest';

let env = "-test" //-dev 或者 -tes
const apiMall = 'https://sujiefs.com/'

//查询轮播图列表
const getAdList = (params) => wxRequest(params, apiMall + '/api/adverts/list');

export default{
    getAdList 
}