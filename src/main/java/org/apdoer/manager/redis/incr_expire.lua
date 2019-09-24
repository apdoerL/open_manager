--[[
自增长+过期时间段,表示在x秒后自动消失
--]]
--key
local c = redis.call('get', KEYS[1]);
if c and tonumber(c) > tonumber(ARGV[1]) then;
    return c;
end
c = redis.call('incr', KEYS[1]);
if tonumber(c) == 1 then
    redis.call('expire', KEYS[1], ARGV[2]);
end
return c;

--[[
local incr_key = KEYS[1];
--过期时间
local expire_time = tonumber(ARGV[1]);
local count = redis.pcall('incr', incr_key);
redis.pcall('expire', incr_key, expire_time);
return tonumber(count);
--]]--
