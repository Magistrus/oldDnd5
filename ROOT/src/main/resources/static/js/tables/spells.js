$(document).ready(function() {
	$('a.toggle-vis').on( 'click', function (e) {
		e.preventDefault();
		var column = table.column( $(this).attr('data-column') );
		column.visible( ! column.visible() );
	});
	var table = $('#spells').DataTable( {
		stateSave: true,
		dom: '<"top"<"left-col"f><"right-col"B>>rti',
		serverSide : true,
		ajax : '/data/spells',
		deferRender: true,
		scrollY: "65vh",
		scrollX: "65vh",
		scrollCollapse: true,
		scroller: true,
		paging: false,
		select: {
			style: 'single'
		},
		autoWidth: false,
		searchPanes: {
            layout: 'columns-4'
        },
		columns : [{
			data : "school",
			width : "1%",
			className: "text-center",
			render : function(data, type) {
				if (type === 'display') {
					var icon = '';
					switch (data) {
					case 'воплощение':
						icon = '<img class="invert" src="/resources/icons/abstract-096.svg" title="воплощение" height="30">';
						break;
					case 'вызов':
						icon = '<img class="invert" src="/resources/icons/abstract-101.svg" title="вызов" height="30">';
						break;
					case 'иллюзия':
						icon = '<img class="invert" src="/resources/icons/abstract-061.svg" title="иллюзия" height="30">';
						break;
					case 'некромантия':
						icon = '<img class="invert" src="/resources/icons/crowned-skull.svg" title="некромантия" height="30">';
						break;
					case 'ограждение':
						icon = '<img class="invert" src="/resources/icons/surrounded-shield.svg" title="ограждение" height="30">';
						break;
					case 'очарование':
						icon = '<img class="invert" src="/resources/icons/charm.svg" title="очарование" height="30">';
						break;
					case 'преобразование':
						icon = '<img class="invert" src="/resources/icons/mighty-spanner.svg" title="преобразование" height="30">';
						break;
					case 'прорицание':
						icon = '<img class="invert" src="/resources/icons/crystal-ball.svg" title="прорицание" height="30">';
						break;
					}
					return icon;
				}
				return data;
			},
		}, 
		{
			data : "level",
			width : "1%",
			className: "text-center"
		},
		{
			data : "heroClass",
			render: {
				_: '[, ].name',
				sp:'[].name'
			},
			width : "1%",
			className: "text-center"
		},
		{
			data : "name",
			width : "20%", 
			render : function(data, type, row) {
				if (type === 'display') {
					var school = '';
					var result = '<h6>' + data + ' <small class="text-secondary">[' + row.englishName + ']';
						if (row.ritual === 'true') {
							result+=' <span data-toggle="tooltip" data-placement="top" title="ритуал" class="badge badge-pill badge badge-success">Р</span>'; 
							}
							if (row.concentration === 'true') {
								result+=' <span data-toggle="tooltip" data-placement="top" title="концентрация" class="badge badge-pill badge-info">К</span>';	
							}
							result+='</small></h6><small>';
							result += '<div class="text-secondary">';
							result += row.school;
							if (row.verbalComponent === '★') {
								result+=' <span data-toggle="tooltip" data-placement="top" title="вербальный" class="badge rounded-pill bg-secondary">В</span>';
							}
							if (row.somaticComponent === '★') {
								result+=' <span data-toggle="tooltip" data-placement="top" title="соматический" class="badge rounded-pill bg-secondary">С</span>';
							}
							if (row.materialComponent === '★') {
								if (row.consumable){
									result+=' <span data-toggle="tooltip" data-placement="top" title="материальный, расходуемый" class="badge rounded-pill bg-dark">М</span>';
								}
								else {
										result+=' <span title="материальный" class="badge rounded-pill bg-secondary">М</span>';	
								}
							}
							result += '</small></div>';
							return result;
						}
						return data;
					}, 
			},
			{
				data : "timeCast",
				width : "1%",
				className: "dt-head-center",
				className: "dt-body-center",
				searchable: false,
			},
			{
				data : "duration",
				width : "1%",
				className: "text-center"
			},
			{
				data : "distance",
				width : "1%",
			    className: "text-center"
			},
			{
				data : "ritual",
				width : "3%",
				className: "dt-head-center",
				className: "dt-body-center",
				render : function(data, type) {
					if (type === 'display') {
						var icon = '';
						switch (data) {
						case 'true':
							icon = '<img class="invert" data-toggle="tooltip" data-placement="top" src="/resources/icons/sands-of-time.svg" title="ритуал" height="25">';
							break;
						}
						return icon;
					}
					return data;
				},
				searchable: false
			},
			{
				data : "concentration",
				render : function(data, type) {
					if (type === 'display') {
						var icon = '';
						switch (data) {
						case 'true':
							icon = '<img class="invert" data-toggle="tooltip" data-placement="top" src="/resources/icons/coma.svg" title="концентрация" height="25">';
							break;
						}
						return icon;
					}
					return data;
				},
				width : "2%",
				className: "text-center",
				searchable: false
			},
			{
				data : "verbalComponent",
				width : "1%",
				className: "text-center",
				searchable: false
			},
			{
				data : "somaticComponent",
				width : "1%",
				className: "text-center",
				searchable: false
			},
			{
				data : "materialComponent",
				width : "1%",
				className: "text-center",
				searchable: false
			},
			{
				data : "damageType",
				width : "2%",
				className: "text-center",
				 searchable: false
			},
			{
				data : "book",
				width : "5%",
				className: "text-center",
				searchable: false
			},
			{
				data : 'englishName',
				width : "1%",
				orderable: false,
			},
			{
				data : 'consumable',
				width : "1%",
				orderable: false,
				searchable: false,
			},
			],
			columnDefs : [{
				"targets": [ 0 ],
				"visible": false
			},
			{
				searchPanes: {
					preSelect: [preSelectClass === null ?  '' : preSelectClass]
				},
				"targets": [ 2 ],
				"visible": false
			},
			{
				"targets": [ 4 ],
				"visible": false
			},
			{
				"targets": [ 5 ],
				"visible": false
			},
			{
				"targets": [ 6 ],
				"visible": false
			},
			{
				"targets": [ 7 ],
				"visible": false
			},
			{
				"targets": [ 8 ],
				"visible": false
			},
			{
				"targets": [ 9 ],
				"visible": false
			},
			{
				"targets": [ 10 ],
				"visible": false
			},
			{
				"targets": [ 11 ],
				"visible": false
			},
			{
				"targets": [ 12 ],
				"visible": false
			},
			{
				"targets": [ 13 ],
				"visible": false
			},
			{
				"targets": [ 14 ],
				"visible": false
			},
			{
				"targets": [ 15 ],
				"visible": false
			},
			],
			buttons: [
	            {
	                extend: 'searchPanes',
	            	className: 'btn-main btn-sm btn-color-main',
	            },
	            {
					extend: 'colvis',
					text: 'Столбцы',
	            	className: 'btn-main btn-sm btn-color-main',
					buttons: [{
						text: 'Школа',
						action: function ( e, dt, node, config ) {
							dt.column( 0 ).visible( ! dt.column( 0 ).visible() );
						}
					},
					{
						text: 'Время',
						action: function ( e, dt, node, config ) {
							dt.column( 4 ).visible( ! dt.column( 4 ).visible() );
						}
					},
					{
						text: 'Длительность',
						action: function ( e, dt, node, config ) {
							dt.column( 5 ).visible( ! dt.column( 5 ).visible() );
						}
					},
					{
						text: 'Дистанция',
						action: function ( e, dt, node, config ) {
							dt.column( 6 ).visible( ! dt.column( 6 ).visible() );
						}
					},
					{
						text: 'Ритуал',
						action: function ( e, dt, node, config ) {
							dt.column( 7 ).visible( ! dt.column( 7 ).visible() );
						}
					},
					{
						text: 'Концентрация',
						action: function ( e, dt, node, config ) {
							dt.column( 8 ).visible( ! dt.column( 8 ).visible() );
						}
					},
					{
						text: 'Компоненты',
						action: function ( e, dt, node, config ) {
							dt.column( 9 ).visible( ! dt.column( 9 ).visible() );
							dt.column( 10 ).visible( ! dt.column( 10 ).visible() );
							dt.column( 11 ).visible( ! dt.column( 11 ).visible() );
						}
					},
					{
						text: 'Тип урона',
						action: function ( e, dt, node, config ) {
							dt.column( 12 ).visible( ! dt.column( 12 ).visible() );
							table.columns.adjust().draw();
						}
					},
					{
						text: 'Источник',
						action: function ( e, dt, node, config ) {
							dt.column( 13 ).visible( ! dt.column( 13 ).visible() );
						}
					}]
				}],
				order : [[1, 'asc']],
				language : {
					processing : "Загрузка...",
					searchPlaceholder: "Поиск ",
					search : "_INPUT_",
					lengthMenu : "Показывать _MENU_ записей на странице",
					zeroRecords : "Ничего не найдено",
					info : "Показано _TOTAL_",
					infoEmpty : "Нет доступных записей",
					infoFiltered : "из _MAX_",
					paginate : {
						first : "В начало",
						previous : "Предыдущая",
						next : "Следущая",
						last : "В конец"
					},
				searchPanes: {
				clearMessage : "Сбросить",
				title : {
						_: 'Отфильтровано - %d',
					0: 'Фильтры не активны (Ctrl или Shift для множественного выбора)',
					1: 'Один фильтр выбран',
				},
				collapse: 'Фильтры (%d)'
			}
		},
		ordering : true,
	});
	$('.dataTables_filter input').addClass('input-dnd');
});