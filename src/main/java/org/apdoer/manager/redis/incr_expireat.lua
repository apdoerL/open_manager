--[[
自增长+过期时间点,表示指定时间点x后自动消失
--]]
--key
local incr_key = KEYS[1];
--过期时间
local expire_time = tonumber(ARGV[1]);
local count = redis.call('incr', incr_key);
redis.call('expireat', incr_key, expire_time);
return tonumber(count);