package tk.rock_n_rolo.rocknrolov1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ldrodriguez on 07/03/2018.
 */

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    //Arreglos

    public  int[] slide_images = {

            R.drawable.eat_icon,
            R.drawable.sleep_icon,
            R.drawable.code_icon

    };

    public String[] slide_headings = {

            "Bares",
            "Locales de ensayo",
            "Tiendas de música"

    };

    public String[] slide_descs = {

            "¿Con ganas de escuchar buena música? Encuentre los bares que hemos descubierto de Rock and Roll y Metal en Bogotá, si no está su preferido, recomiéndelo!",
            "Sabrá las curiosidades, potenciales y aspectos por mejorar de los sitios de ensayo. Podrá separar los espacios desde su propio teléfono e invitar a todos los que quiera que lo acompañen al ensayo para que reciban las notificaciones del evento.",
            "Lo pondremos al tanto de promociones, ofertas y descuentos de las tiendas de música que cuenten con generalidades e instrumentos propios del Rock and Roll y el Metal en Bogotá."

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((RelativeLayout)object);
    }
}
