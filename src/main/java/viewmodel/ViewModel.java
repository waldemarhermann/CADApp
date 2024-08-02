package viewmodel;

import view.IView;

public abstract class ViewModel {
    protected IView view = null;

    public void registerView(IView view) {
        this.view = view;
    }
}
