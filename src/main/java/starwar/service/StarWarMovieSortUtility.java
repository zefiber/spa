package starwar.service;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import starwar.models.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("StarWarMovieSortUtility")
public class StarWarMovieSortUtility{

	private static final Comparator<Movie> SORT_BY_EPISODE = new EpisodeComparator();
	private static final Comparator<Movie> SORT_BY_RELEASE = new ReleaseComparator();
	private static final Comparator<Movie> SORT_BY_MACHETE = new MacheteComparator();


	public List<Movie> sortMovieByParam(List<Movie> list, String sortParam){
		if(list == null){
			return null;
		}

		List<Movie> sortedList = new ArrayList<>(list);

		if("episode".equalsIgnoreCase(sortParam)){
			Collections.sort(sortedList, SORT_BY_EPISODE);
		}else if("release".equalsIgnoreCase(sortParam)){
			Collections.sort(sortedList, SORT_BY_RELEASE);
		}else{
			Collections.sort(sortedList, SORT_BY_MACHETE);
		}
		return sortedList;
	}

	private static final class EpisodeComparator implements Comparator<Movie>, Serializable
	{

		@Override public int compare(Movie o1, Movie o2) {
				if (o1.getEpisode() == null && o2.getEpisode() == null)
                    return 0;
                if (o1.getEpisode() == null)
                    return 1;
                else if (o2.getEpisode() == null)
                    return -1;
			return Integer.parseInt(o1.getEpisode()) - Integer.parseInt(o2.getEpisode());
		}
	}

	private static final class ReleaseComparator implements Comparator<Movie>, Serializable
	{

		@Override public int compare(Movie o1, Movie o2) {
			return Integer.parseInt(o1.getRelease()) - Integer.parseInt(o2.getRelease());
		}
	}

	private static final class MacheteComparator implements Comparator<Movie>, Serializable
	{

		@Override public int compare(Movie o1, Movie o2) {
			if (o1.getMachete() == null && o2.getMachete() == null)
                    return 0;
                if (o1.getMachete() == null)
                    return 1;
                else if (o2.getMachete() == null)
                    return -1;
			return Integer.parseInt(o1.getMachete()) - Integer.parseInt(o2.getMachete());
		}
	}
}
