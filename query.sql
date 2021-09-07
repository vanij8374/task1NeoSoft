SELECT
    p.id,
    p.name,
    coms.content
FROM
         post p
    JOIN author  a ON p.authorid = a.id
    JOIN (
        SELECT
            id,
            content,
            postid,
            ROW_NUMBER()
            OVER(PARTITION BY postid
                 ORDER BY createdts DESC
            ) AS maxcomments
        FROM
            comments
    )       coms ON coms.postid = p.id 
    where a.name='James Bond' and coms.maxcomments<=10