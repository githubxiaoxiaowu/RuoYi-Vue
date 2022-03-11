import request from '@/utils/request'

// 查询抖音营销记录列表
export function listPromote(query) {
  return request({
    url: '/tiktok/promote/list',
    method: 'get',
    params: query
  })
}

// 查询抖音营销记录详细
export function getPromote(id) {
  return request({
    url: '/tiktok/promote/' + id,
    method: 'get'
  })
}

// 新增抖音营销记录
export function addPromote(data) {
  return request({
    url: '/tiktok/promote',
    method: 'post',
    data: data
  })
}

// 修改抖音营销记录
export function updatePromote(data) {
  return request({
    url: '/tiktok/promote',
    method: 'put',
    data: data
  })
}

// 删除抖音营销记录
export function delPromote(id) {
  return request({
    url: '/tiktok/promote/' + id,
    method: 'delete'
  })
}
