set  "tag_name=v1.0.0"


git tag -d %tag_name%

git push origin --delete %tag_name%

git tag %tag_name%

git push origin %tag_name%


