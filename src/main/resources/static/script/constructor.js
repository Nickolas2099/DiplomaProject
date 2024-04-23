// function myFunction() {
//     var popup = document.getElementById("myPopup");
//     popup.classList.toggle("show");
// }

const app = Vue.createApp({
    data() {
        return {
            tables: [],
            selectedTable: null,
            fields: [],
            rows: null,
            checkedFields: [],
            sorts: [
                {
                    field: 'Поле',
                    sortType: 'Операция'
                }
            ],
            savedSorts: null,
            filters: [{
                    field: {
                        userTitle: 'Поле', 
                        techTitle: null,
                        type: null
                    },
                    condition: {
                        title: 'Операция',
                        isThereField: false
                    },
                    conditionField: null
                }
            ],
            savedFilters: null,
            conditions: {
                row: [{title: 'LIKE', isThereField: true}, {title: 'NOT LIKE', isThereField: true}, {title: 'Содержит', isThereField: true}, 
                {title: 'Начинается с', isThereField: true}, {title: 'Заканчивается на', isThereField: true}, {title: 'IS NOT NULL', isThereField: false}, 
                {title: 'IS NULL', isThereField: false}],
                
                number: [{title: '=', isThereField: true}, {title: '!=', isThereField: true}, {title: '>', isThereField: true},
                {title: '<', isThereField: true}, {title: '<=', isThereField: true}, {title: '>=', isThereField: true}, {title: 'IS NOT NULL', isThereField: false}, 
                {title: 'IS NULL', isThereField: false}],

                time: [{title: 'До', isThereField: true}, {title: 'После', isThereField: true}

                ]
            },
            groups: [{
                field: 'Поле'
            }],
            savedGroups: null,
            agregations: [{
                operation : 'Операция',
                field: {
                    userTitle: 'Поле',
                    techTitle: '',
                    type: ''
                }
            }],
            savedAggregations: null,
            operations: {
                row: [{title: 'count'}],
                number: [{title: 'min'}, {title: 'max'}, {title: 'avg'}, {title: 'sum'}, {title: 'count'}],
                time: [{title: 'count'}]
            },
            joins: [{
                field: {
                    userTitle: 'Поле',
                    techTitle: '',
                    type: ''
                },
                table: {
                    userTitle: 'Таблица',
                    techTitle: ''
                },
                tableField: {
                    userTitle: 'Поле',
                    techTitle: '',
                    type: ''
                }
            }],
            savedJoins: null,
            limit: "10", 
            errorText: ''
        };
    },
    methods: {
        updateFields(event) {
            this.fields = this.tables.find(table => table.userTitle === event.target.value).fields;
            this.checkedFields = [];
            this.savedSorts = null;
            this.savedFilters = null;
            this.savedGroups = null;
        },
        showFilter() {
            document.getElementById("filter").style.display="block";
        },
        closeFilter() {
            document.getElementById("filter").style.display="none";
        },
        addFilter() {
                if(this.filters.length < 3) {
                    this.filters.push({
                    field: {
                        userTitle: 'Поле', 
                        techTitle: null,
                        type: null
                    },
                    condition: {
                        title: 'Операция',
                        isThereField: false
                    },
                    conditionField: null
                })
            }
        },
        removeFilter(field, condition, conditionField) {
                if(this.filters.length > 1) {
                    let index = this.filters.findIndex(f => f.field === field && f.condition === condition && f.conditionField === conditionField);
                    if (index !== -1) {
                        this.filters.splice(index, 1);
                    }
                }
        },
        removeSavedFilter(field, condition, conditionField) {
            let index = this.savedFilters.findIndex(f => f.field === field && f.condition === condition && f.conditionField === conditionField);
            if (index !== -1) {
                this.savedFilters.splice(index, 1);
            }
            if(this.savedFilters.length == 0) {
                this.savedFilters = null;
            }
        },
        acceptFilters(event) {
            this.savedFilters = this.filters.filter(f => f.field !== 'Поле' && f.condition !== 'Операция');
            document.getElementById("filter").style.display="none";
        },
        onChangeFieldFromFilter(filter) {
                let index = this.filters.findIndex(f => f === filter);
                if(index !== -1) {
                    this.filters[index].condition = {
                        title: 'Операция',
                        isThereField: false
                    };
                this.filters[index].conditionField = null;  
            }
        },
        showSort() {
            document.getElementById("sortMenu").style.display="block";
        },
        closeSort() {
            document.getElementById("sortMenu").style.display="none";
            this.sorts = [{
                    field: 'Поле',
                    sortType: 'Операция'
                }]
        },
        acceptSort() {
            this.savedSorts = this.sorts.filter(sort => sort.field !== 'Поле' && sort.sortType !== 'Операция');
            document.getElementById("sortMenu").style.display="none";
        },
        addSort() {
            if(this.sorts.length < 3) {
                    this.sorts.push({
                        field: 'Поле',
                        sortType: 'Операция'
                });
            }
        },
        removeSort(field, sortType) {
            let index = this.sorts.findIndex(s => s.field === field && s.sortType === sortType);
            if (index !== -1) {
                this.sorts.splice(index, 1);
            }
        },
        removeSavedSort(field, sortType) {
            let index = this.savedSorts.findIndex(s => s.field === field && s.sortType === sortType);
            if (index !== -1) {
                this.savedSorts.splice(index, 1);
            }
            if(this.savedSorts.length == 0) {
                this.savedSorts = null;
            }
        },
        showGroup() {
            document.getElementById("group").style.display="block";
        },
        closeGroup() {
            document.getElementById("group").style.display="none";
        },
        addGroup() {
            if(this.groups.length < 3) {
                this.groups.push({
                    field: 'Поле'
                });
                
            }
        },
        removeGroup(field) {
            let index = this.groups.findIndex(g => g.field === field);
            if (index !== -1) {
                this.groups.splice(index, 1);
            }
        },
        acceptGroups() {
            const uniqueFields = new Set();
            this.savedGroups = this.groups.filter(g => {
              if (g.field !== 'Поле' && !uniqueFields.has(g.field)) {
                uniqueFields.add(g.field);
                return true;
              }
              return false;
            });
            
            document.getElementById("group").style.display = "none";
        },
        acceptAllGroup() {
            this.savedGroups = [{
                field: '*'
            }]
            document.getElementById("group").style.display="none";
        },
        removeSavedGroup(field) {
            let index = this.savedGroups.findIndex(g => g.field === field);
            if (index !== -1) {
                this.savedGroups.splice(index, 1);
            }
            if(this.savedGroups.length == 0) {
                this.savedGroups = null;
            }
        },
        showAgregation() {
            document.getElementById("agregation").style.display = "block";
        },
        closeAgregation() {
            document.getElementById("agregation").style.display = "none";
        },
        addAggregation() {
            if(this.agregations.length < 3) {
                this.agregations.push({
                    operation : 'Операция',
                    field: {
                        userTitle: 'Поле',
                        techTitle: '',
                        type: ''
                    }
                });
            }
        },
        removeAgregation(agregation) {
            let index = this.agregations.findIndex(a => a === agregation);
            if (index !== -1) {
                this.agregations.splice(index, 1);
            }
        },
        acceptAggregations() {
            
            let savedAggregations = [];
            for (let i = 0; i < this.agregations.length; i++) {
                let aggregation = this.agregations[i];
                if(aggregation.field.techTitle == '' || aggregation.operation == '') {
                    continue;
                }
                savedAggregations.push({ operation: aggregation.operation + '(' + aggregation.field.techTitle + ')'});
            }
            this.savedAggregations = savedAggregations;
            document.getElementById("agregation").style.display = "none";
        },
        removeSavedAggregation(aggr) {
            let index = this.savedAggregations.findIndex(a => a === aggr);
            if (index !== -1) {
                this.savedAggregations.splice(index, 1);
            }
        },
        assembleFilters() {
            if(this.savedFilters != null) {
                let filters = [];
                for(let i = 0; i < this.savedFilters.length; i++) {
                    let filter = {
                        field: null,
                        condition: null,
                        conditionField: null
                    };
                    filter.field = this.savedFilters[i].field.techTitle;
                    switch(this.savedFilters[i].condition.title) {
                        case 'Начинается с': {
                            filter.condition = 'LIKE CONCAT(\'' + this.savedFilters[i].conditionField + '\', \'%\')';
                            break;
                        }
                        case 'Заканчивается на': {
                            filter.condition = 'LIKE CONCAT(\'%\', \'' + this.savedFilters[i].conditionField + '\')';
                            break;
                        }
                        case 'Содержит': {
                            filter.condition = 'LIKE CONCAT(\'%\', \'' + this.savedFilters[i].conditionField + '\', \'%\')';
                            break;
                        }

                        default : {
                            console.log(this.savedFilters[i].condition.title);
                            filter.condition = this.savedFilters[i].condition.title;
                            filter.conditionField = this.savedFilters[i].conditionField;
                            break;
                        }
                    }
                    filters.push(filter);
                    return filters;
                }
            } else {
                return null;
            }
        },
        showJoinMenu() {
            document.getElementById("join").style.display="block";
        },
        closeJoinMenu() {
            document.getElementById("join").style.display="none";
        },
        acceptJoins() {
            this.savedJoin = this.joins.filter(j => j.field !== 'Поле' && j.table !== 'Таблица' && j.tableField !== 'Поле');
            document.getElementById("join").style.display = "none";
        },
        sendQuery() {

            let filters = this.assembleFilters();
           
            const query = {
                dbTitle: localStorage.getItem('dbTitle'),
                table: this.selectedTable,
                fields: this.checkedFields.map(item => item.techTitle),
                filters: filters,
                sorts: this.savedSorts,
                groups: this.savedGroups,
                aggregations: this.savedAggregations,
                limit: this.limit,
            }
            console.log(query);
            axios.post('http://localhost:8080/api/v1/connectedDbs/select', 
                query,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('jwt')}`
                    }
                }
            ).then(response => {
                this.rows = response.data.data;
                this.errorText = '';
            }).catch(error => {
                this.errorText = 'Данных по результату запроса не найдено'
                console.error(error);
            });
        },
        goDbData() {
            window.location.href = 'dbData.html';
        },
        async exportSelect() {
            try {
                let req = {
                rows: this.rows
                };
                const response = await axios.post('http://localhost:8080/api/v1/export/excelFile', req, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('jwt')}`
                },
                responseType: 'blob'
                });

                if (response.status === 200) {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'select.xlsx');
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            } catch (error) {
                console.error(error);
                alert('Произошла ошибка при запросе к серверу');
            }
            }
    },
    async created() {
        const response = await axios.get('http://localhost:8080/api/v1/connectedDbs/table', {
                params: {
                    dbTitle: localStorage.getItem('dbTitle')
                },
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('jwt')}`
                }
        })
        this.tables = response.data.data;
        const typeMapping = {

            'tinyint': 'number',
            'smallint': 'number',
            'mediumint': 'number',
            'int': 'number',
            'integer': 'number',
            'bigint': 'number',
            'float': 'number',
            'double': 'number',
            'char': 'row',
            'character varying': 'row',
            'varchar': 'row',
            'text': 'row',
            'date': 'time',
            'datetime': 'time',
            'timestamp': 'time',
            'time': 'time',
            'year': 'time'
        };

        this.tables.forEach(table => {
            table.fields.forEach(field => {
                if (typeMapping.hasOwnProperty(field.type)) {
                    field.type = typeMapping[field.type];
                }
            });
        });
        this.selectedTable = this.tables[0] || null;
    }
});
app.mount('#contructor');