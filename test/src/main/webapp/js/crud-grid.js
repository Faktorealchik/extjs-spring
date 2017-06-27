Ext.onReady(function () {
    var Person = Ext.data.Record.create([
        {
            name: 'id',
            type: 'int',
            useNull: true
        }, {
            name: 'name',
            type: 'string'
        }, {
            name: 'phone',
            type: 'string'
        }, {
            name: 'email',
            type: 'string'
        }]);

    var proxy = new Ext.data.HttpProxy({
        api: {
            read: '/person/view.action',
            create: '/person/create.action',
            update: '/person/update.action',
            destroy: '/person/delete.action'
        }
    });
    var reader = new Ext.data.JsonReader({
            totalProperty: 'total',
            successProperty: 'success',
            idProperty: 'id',
            root: 'data'
        },
        Person);

    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
    });

    var store = new Ext.data.Store({
        model: 'Person',
        proxy: proxy,
        reader: reader,
        writer: writer,
        autoSave: true
    });

    //read the data from json array
    store.load();

    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });

    var grid = new Ext.grid.GridPanel({
        store: store,
        plugins: [editor],
        title: 'Persons',
        height: 400,
        width: 535,
        frame: true,
        columns: [
            {
                header: "ID",
                width: 50,
                sortable: true,
                dataIndex: 'id'
            },
            {
                header: "NAME",
                width: 150,
                sortable: true,
                dataIndex: 'name',
                editor: {
                    allowBlank: false //set require param
                }
            },
            {
                header: "PHONE",
                width: 150,
                sortable: true,
                dataIndex: 'phone',
                editor: {
                    allowBlank: false
                }
            },
            {
                header: "EMAIL",
                width: 160,
                sortable: true,
                dataIndex: 'email',
                editor: {
                    allowBlank: false
                }
            }
        ],
        tbar: [{
            text: 'Add Contact',
            width: 255,
            handler: function () {
                var e = new Person({
                    name: 'New Guy',
                    phone: '+7-(000)-000-00-00',
                    email: 'example@mail.ru'
                });
                store.insert(0, e);
                editor.stopEditing();
                location.href = 'http://localhost:8080';
            }
        }, {
            text: 'Remove Contact',
            width: 255,
            handler: function () {
                var s = grid.getSelectionModel().getSelections();
                for (var i = 0, r; r = s[i]; i++) {
                    store.remove(r);
                }
            }
        }]
    });

    grid.render('crud-grid');
});