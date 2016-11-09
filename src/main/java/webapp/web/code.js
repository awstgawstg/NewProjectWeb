$(function(){ // on dom ready

    var cy = cytoscape({
        container: document.getElementById('cy'),

        boxSelectionEnabled: false,
        autounselectify: true,

        style: cytoscape.stylesheet()
            .selector('node')
            .css({
                'content': 'data(id)',
                'label':'data(id)',
                'background-color': '#666'
            })
            .selector('dataset')
            .css({
                'content': 'data(id)',
                'label':'data(id)',
                'background-color': '#666'
            })
            .selector('edge')
            .css({
                'target-arrow-shape': 'triangle',
                'width': 4,
                'line-color': '#61bffc',
                'target-arrow-color': '#61bffc',
                'curve-style': 'bezier',
                'label':'data(label)'
            })
            .selector('.highlighted')
            .css({
                'background-color': '#61bffc',
                'line-color': '#61bffc',
                'target-arrow-color': '#61bffc',
                'transition-property': 'background-color, line-color, target-arrow-color',
                'transition-duration': '0.5s'
            }),

        elements: {
            nodes: [
                { data: { id: 'user1' } },
                { data: { id: 'user2' } },
                { data: { id: 'user3' } },
                { data: { id: 'user4' } },
                { data: { id: 'user5' } },
                { data: { id: 'service1' } },
                { data: { id: 'service2' } },
                { data: { id: 'service3' } },
                { data: { id: 'service4' } },
                { data: { id: 'service5' } },
                { data: { id: 'dataset1' } },
                { data: { id: 'dataset2' } },
                { data: { id: 'dataset3' } },
                { data: { id: 'dataset4' } },
                { data: { id: 'dataset5' } },
                { data: { id: 'dataset6' } },
                { data: { id: 'dataset7' } },
                { data: { id: 'service6' } }

            ],
            edges:[

            ]









        }
    });


    var options = {
        name: 'grid',

        fit: true, // whether to fit the viewport to the graph
        padding: 30, // padding used on fit
        boundingBox: undefined, // constrain layout bounds; { x1, y1, x2, y2 } or { x1, y1, w, h }
        avoidOverlap: true, // prevents node overlap, may overflow boundingBox if not enough space
        avoidOverlapPadding: 10, // extra spacing around nodes when avoidOverlap: true
        condense: false, // uses all available space on false, uses minimal space on true
        rows: undefined, // force num of rows in the grid
        cols: undefined, // force num of columns in the grid
        position: function( node ){}, // returns { row, col } for element
        sort: undefined, // a sorting function to order the nodes; e.g. function(a, b){ return a.data('weight') - b.data('weight') }
        animate: false, // whether to transition the node positions
        animationDuration: 500, // duration of animation in ms if enabled
        animationEasing: undefined, // easing of animation if enabled
        ready: undefined, // callback on layoutready
        stop: undefined // callback on layoutstop
    };

    cy.layout( options );




    /** var first = cy.elements().bfs('#a', function(){}, true);
     var second = cy.elements().bfs('#f', function(){}, true);

     var jNhd = cy.$('#j').neighborhood();


     //alert(start1.neighborhood().nodes()[0].id);
     list = []
     function highlightNextEle(root,number){
        if( number < root.path.length){
            if( list.indexOf(root.path[number].data('id'))>=0){

                setTimeout(function() {highlightNextEle(root,number+1)}, 2000);
                return;}
            if(list.indexOf(String(root.path[number].data('target')))>=0){
                setTimeout(function() {highlightNextEle(root,number+1)}, 2000);
                return;
            }
            if(root.path[number].data('id').length<2)
                list.push(root.path[number].data('id'));
            root.path[number].addClass('highlighted');
            if (root.path[number].data('target') != undefined){
                console.log(root.path[number].data('target'))
                console.log(list)
                var newnode = '#'+root.path[number].data('target');
                console.log("newnode comes" + newnode)
                var tmp = cy.elements().bfs(newnode, function(){}, true);
                setTimeout(function() {highlightNextEle(tmp,0)}, 2000);

            }

            setTimeout(function() {highlightNextEle(root,number+1)}, 2000);
        }
    };










     // kick off first highlight
     highlightNextEle(first,0);
     highlightNextEle(second,0);

     **/
    function addedge(source,target,label,weight,time){
        setTimeout(
            function(){
                cy.add([
                    {group: "edges", data: {id: source+target, source: source, target: target, label: label+" weight: "+weight.toString(),weight:weight}}
                ]);
            },time);
    }

    function follow(source,target,label,time){
        setTimeout(
            function(){
                cy.add([
                    {group: "edges", data: {id: source+target, source: source, target: target, label: label}}
                ]);
            },time);
    }





    var time = 0
    function pass(x){
        time=time+x
        return time
    }

    addedge('user1','service2','used',1,pass(2000));
    addedge('service2','dataset4','connected',1,pass(2000));
    addedge('user1','dataset4','used',1,pass(1000));
    addedge('user3','service2','used',7,pass(2000));
    addedge('service2','dataset5','connected',7,pass(2000));
    addedge('user3','dataset5','used',7,pass(1000));
    follow('user4','user3','follow',pass(2000));
    addedge('user4','dataset5','used',0,pass(500));
    addedge('user4','service2','used',0,pass(500));



    cy.layout( options );






}); // on dom ready