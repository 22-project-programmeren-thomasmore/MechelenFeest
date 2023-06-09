function initMap(){

    const map = new google.maps.Map(document.getElementById('map'), {
        zoom:13,
        center:{lat:51.02574,lng:4.47762},
    });

    new google.maps.Marker({
        position: {lat:51.02562614947713, lng: 4.475430932544553},
        map,
        title: "You are here",
    })


    setFestivalMarker(map);
    setWcMarker(map);
    setFoodStallMarker(map);
}


const festivals = [
    ["Parkpop",51.02454308361425, 4.484819823293105],
    ["Maanrock",51.02846281757062, 4.47997101265373],
    ["De Koer",51.02877370904206, 4.476658615751852],
    ["Hap Food Festival",51.04657353762093, 4.470798429963242],
];


const toilettes = [
    [51.02064981224487, 4.459200462302486],
    [51.017775719967595, 4.460031431982612],
    [51.03239321544294, 4.485537780434319],
    [51.02589677125681, 4.493014860306914],
    [51.0477707944521, 4.4750089625724065],
]

const foodstalls = [
    [51.0274903627082, 4.484276835344814],
    [51.01998175354514, 4.482687798396541],
    [51.03265797537755, 4.472517709075864],
    [51.026836650347704, 4.465553989264736],
    [51.047896672953954, 4.472713185024519],
]


function setFestivalMarker(map){

    for (let i = 0; i < festivals.length; i++) {
        const festival = festivals[i];


        new google.maps.Marker({
            position: { lat: festival[1], lng: festival[2] },
            map,
            icon: "/img/paper-festoon.png",
            title: festival[0],
        });

    }


}


function setWcMarker(map) {

    for (let i = 0; i<toilettes.length; i++) {
        const wc = toilettes[i];

        new google.maps.Marker({
            position: {lat: wc[0], lng: wc[1]},
            map,
            icon: "/img/toilet.png",
            title: "WC",
        })

    }

}



function setFoodStallMarker(map) {

    for (let i = 0; i<foodstalls.length; i++) {
        const foodstall = foodstalls[i];

        new google.maps.Marker({
            position: {lat: foodstall[0], lng: foodstall[1]},
            map,
            icon: "/img/shop.png",
            title: "Food Stall",
        })

    }

}


window.initMap = initMap;