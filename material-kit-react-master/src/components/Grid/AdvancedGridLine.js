//advancedGridList begin
import React, {useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import GridList from '@material-ui/core/GridList';
import GridListTile from '@material-ui/core/GridListTile';
import GridListTileBar from '@material-ui/core/GridListTileBar';
import IconButton from '@material-ui/core/IconButton';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import axios from 'axios';

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    overflow: 'hidden',
    backgroundColor: theme.palette.background.paper,
  },
  gridList: {
    width: 500,
    height: 450,
    // Promote the list into his own layer on Chrome. This cost memory but helps keeping high FPS.
    transform: 'translateZ(0)',
  },
  titleBar: {
    background:
      'linear-gradient(to bottom, rgba(0,0,0,0.7) 0%, ' +
      'rgba(0,0,0,0.3) 70%, rgba(0,0,0,0) 100%)',
  },
  icon: {
    color: 'white',
  },
}));



export default function AdvancedGridList(props) {
    const classes = useStyles();
    const [data, setData] = useState([]);
    const [sortState, setSortState] = useState(props);

  function storeFavouriteMovie(imdbId){
    console.log("move:"+imdbId);
    axios.get("http://localhost:9090/storeFavouriteMovie",
      {params: { imdbId: imdbId }}
    )
    .then((response) => {
      console.log(response);
    }, (error) => {
      console.log(error);
    });
  }  

  useEffect(() => {
      const fetchData = async() => {
      const result = await axios('http://localhost:9090/retrieveMovieList?sortParam=' + sortState.sort);
      setData(result.data);
      console.log(result.data)
    };
      setSortState(props);
      fetchData();
  }, [props]);

 
  
    return (
      <div className={classes.root}>
        <GridList cellHeight={500} spacing={10} className={classes.gridList}>
          {data.map(tile => (
            <GridListTile key={tile.imdbId} cols={2} rows={1}>
              <img src={tile.image} alt={tile.title} />
              <GridListTileBar
                title={tile.movieTitle}
                titlePosition="top"
                subtitle={tile.actors}
                actionIcon={
                  <IconButton onClick={()=> storeFavouriteMovie(tile.imdbId)} aria-label={`star ${tile.title}`} className={classes.icon}>
                    <StarBorderIcon/>
                  </IconButton>
                }
                actionPosition="left"
                className={classes.titleBar}
              />
            </GridListTile>
          ))}
        </GridList>
      </div>
    );
  }