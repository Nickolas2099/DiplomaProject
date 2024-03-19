function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}

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
                {title: 'IS NULL', isThereField: false}]
            },
            group: null,
            limit: "10" 
        };
    },
    methods: {
        updateFields(event) {
            this.fields = this.tables.find(table => table.userTitle === event.target.value).fields;
            this.checkedFields = [];
            this.savedSorts = null;
            this.savedFilters = null;
            this.group = null;
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
            this.sorts = [
                    {
                        field: 'Поле',
                        sortType: 'Операция'
                    }
                ]
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
            let field = document.getElementById('fieldForGroup').value;
            if(field != 'Поле') {
                this.group = field;
                document.getElementById("group").style.display="none";
            }
        },
        removeGroup() {
            this.group = null;
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
                    console.log(filter);
                    return filters;
                }
            } else {
                return null;
            }
        },
        sendQuery() {

            let filters = this.assembleFilters();
           
            const query = {
                dbTitle: localStorage.getItem('dbTitle'),
                table: this.selectedTable,
                fields: this.checkedFields.map(item => item.techTitle),
                filters: filters,
                sorts: this.savedSorts,
                group: this.group,
                limit: this.limit,
            }
            // console.log(query);
            axios.post('http://localhost:8080/api/v1/connectedDbs/select', 
                query,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('jwt')}`
                    }
                }
            ).then(response => {
                this.rows = response.data.data;
                // console.log(response.data.data);
            }).catch(error => {
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
                responseType: 'blob' // Указываем, что ожидаем получить данные в виде Blob
                });

                if (response.status === 200) {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    // Создаем ссылку и кликаем по ней для загрузки файла
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'select.xlsx');
                    document.body.appendChild(link);
                    link.click();
                    // Удаляем ссылку после загрузки
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
            'bigint': 'number',
            'float': 'number',
            'double': 'number',
            'char': 'row',
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
        // console.log(this.tables);
    }
});
app.mount('#contructor');