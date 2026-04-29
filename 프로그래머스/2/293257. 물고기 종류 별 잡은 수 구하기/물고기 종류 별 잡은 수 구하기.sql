-- 코드를 작성해주세요
select 
    count(i.fish_type) as FISH_COUNT,
    n.fish_name as FISH_NAME
from fish_info i
join fish_name_info n on i.fish_type = n.fish_type
group by i.fish_type
order by 1 desc;