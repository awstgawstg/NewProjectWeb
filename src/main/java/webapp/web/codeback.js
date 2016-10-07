/**
 * Created by dingzhang on 9/30/16.
 */
$(function(){ // on dom ready

    var cy = cytoscape({
        container: document.getElementById('cy'),

        boxSelectionEnabled: false,
        autounselectify: true,

        style: cytoscape.stylesheet()
            .selector('node')
            .css({
                'content': 'data(id)'
            })
            .selector('edge')
            .css({
                'target-arrow-shape': 'triangle',
                'width': 4,
                'line-color': '#ddd',
                'target-arrow-color': '#ddd',
                'curve-style': 'bezier'
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
                { data: { id: 'a' } },
                { data: { id: 'b' } },
                { data: { id: 'c' } },
                { data: { id: 'd' } },
                { data: { id: 'e' } },
                { data: { id: 'f' } },
                { data: { id: 'g' } },
                { data: { id: 'h' } },
                { data: { id: 'i' } },
                { data: { id: 'j' } },
                { data: { id: 'k' } },
                { data: { id: 'l' } },
                { data: { id: 'm' } },
                { data: { id: 'n' } },
                { data: { id: 'o' } },
                { data: { id: 'p' } },
                { data: { id: 'r' } },
                { data: { id: 's' } },

            ],

            edges: [
                { data: { id: 'ae', weight: 1, source: 'a', target: 'e' } },
                { data: { id: 'ea', weight: 1, source: 'e', target: 'a' } },
                { data: { id: 'ab', weight: 1, source: 'a', target: 'b' } },
                { data: { id: 'ba', weight: 1, source: 'b', target: 'a' } },
                { data: { id: 'be', weight: 1, source: 'b', target: 'e' } },
                { data: { id: 'bc', weight: 1, source: 'b', target: 'c' } },
                { data: { id: 'ce', weight: 1, source: 'c', target: 'e' } },
                { data: { id: 'cd', weight: 1, source: 'c', target: 'd' } },
                { data: { id: 'de', weight: 1, source: 'd', target: 'e' } },
                { data: { id: 'oc', weight: 1, source: 'o', target: 'c' } },
                { data: { id: 'mn', weight: 1, source: 'm', target: 'n' } },
                { data: { id: 'lo', weight: 1, source: 'l', target: 'o' } },
                { data: { id: 'gh', weight: 1, source: 'g', target: 'h' } },
                { data: { id: 'hg', weight: 1, source: 'h', target: 'g' } },
                { data: { id: 'eg', weight: 1, source: 'e', target: 'g' } },
                { data: { id: 'gi', weight: 1, source: 'g', target: 'i' } },
                { data: { id: 'ja', weight: 1, source: 'j', target: 'a' } },
                { data: { id: 'aj', weight: 1, source: 'a', target: 'j' } },
                { data: { id: 'fc', weight: 1, source: 'f', target: 'c' } },
                { data: { id: 'cf', weight:1, source: 'c', target: 'f' } },
                { data: { id: 'kd', weight: 1, source: 'k', target: 'd' } },
                { data: { id: 'ne', weight: 1, source: 'n', target: 'e' } },
                { data: { id: 'en', weight: 1, source: 'e', target: 'n' } },
                { data: { id: 'ao', weight: 1, source: 'a', target: 'o' } },
                { data: { id: 'pn', weight: 1, source: 'p', target: 'n' } },
                { data: { id: 'sd', weight: 1, source: 's', target: 'd' } },
                { data: { id: 'ds', weight: 1, source: 'd', target: 's' } },
                { data: { id: 'rk', weight: 1, source: 'r', target: 'k' } },






            ]
        },

        layout: {
            name: 'breadthfirst',
            directed: true,
            roots: '#a',
            padding: 10
        }
    });

    var first = cy.elements().bfs('#a', function(){}, true);
    var second = cy.elements().bfs('#f', function(){}, true);



    function highlightNextEle(root,number){
        if( number < root.path.length ){
            root.path[number].addClass('highlighted');
            highlightNextEle(root,number+1)
        }
    };







// kick off first highlight
    setTimeout(highlightNextEle(first,0), 3000);
    setTimeout(highlightNextEle(second,0), 3000);




}); // on dom ready