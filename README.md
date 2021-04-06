# exonDoc
## Document oriented database 
*java prototipe*

DB use exon format:  
'Tom' - string  
25 - number  
true - boolean  
"2007-12-03T10:15:30" - date  
\`/exdoc/storage\` - disk path  
{} - object  
[] - array  

See:  
[McKeeman Form](https://github.com/fitincontact/exonDoc/blob/main/manual/exon)  
[Recursive form](https://github.com/fitincontact/exonDoc/blob/main/manual/exon_steps.png)  

example:  
{  
name : 'David'  
birthDate : "2007-12-03T10:15:30"  
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
    - [X] exon beautifier
- [ ] Storage on disc
    - [ ] read
    - [ ] write
- [ ] DML
    - [ ] insert
    - [ ] select
    - [ ] update
    - [ ] delete
