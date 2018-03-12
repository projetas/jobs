import { CarsUiPage } from './app.po';

describe('cars-ui App', () => {
  let page: CarsUiPage;

  beforeEach(() => {
    page = new CarsUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
