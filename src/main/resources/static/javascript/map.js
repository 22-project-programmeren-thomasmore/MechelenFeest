function initMap(){

    const map = new google.maps.Map(document.getElementById('map'), {
        zoom:14,
        center:{lat:51.02574,lng:4.47762},
    });



    setFestivalMarker(map);
    setWcMarker(map);
}


const festivals = [
    ["Parkpop",51.02454308361425, 4.484819823293105],
    ["Maanrock",51.02846281757062, 4.47997101265373],
    ["De Koer",51.02877370904206, 4.476658615751852],
];


const toilettes = [
    [51.02064981224487, 4.459200462302486],
    [51.017775719967595, 4.460031431982612],
    [51.03239321544294, 4.485537780434319],
    [51.02589677125681, 4.493014860306914],
]

const foodstalls = [
    [51.0274903627082, 4.484276835344814],
    [51.01998175354514, 4.482687798396541],
    [51.03265797537755, 4.472517709075864],
    [51.026836650347704, 4.465553989264736],
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



window.initMap = initMap;