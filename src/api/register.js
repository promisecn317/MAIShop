import request from '../utils/request';

export function getEmailCode (data){
  return request({
    url:'/api/register/getEmailCode',
    method: 'POST',
    data
  })
}

export function register (code,data){
  return request({
    url:'/api/register?code=' +code,
    method:'POST',
    data
  })
}
