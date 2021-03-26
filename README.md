# exonDoc
## Document oriented database 
*java prototipe*

DB use exon format:  
'Tom' - string  
25 - number  
true - boolean  
"1985-12-01 12:01:00" - date  
\`/exdoc/storage\` - disk path  
{} - object  
[] - array  

See:  
[McKeeman Form](https://github.com/fitincontact/exonDoc/blob/main/manual/exon)  
[Recursive form](https://github.com/fitincontact/exonDoc/blob/main/manual/exon_steps.png)  

example:  
{  
name : 'David'  
birthDate : "1985-12-01 12:01:00"  
age : 25  
isDeveloper : true  
foto : \`/exdoc/storage\`  
friends : [  
{name: 'Sarah'}  
{name: 'John'}  
]  
}

- [ ] Parser
    - [X] exon
    - [ ] command
    - [X] exon to string
- [ ] DML
    - [ ] insert
    - [ ] select
    - [ ] update
    - [ ] delete
